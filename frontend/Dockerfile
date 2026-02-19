# Use official Node.js image as base
FROM node:18-alpine

WORKDIR /app

# Copy package files
COPY package.json package-lock.json* ./

# Install dependencies
RUN npm install

# Copy source code
COPY . .

# Expose port for dev server
EXPOSE 5173

# Run dev server with host 0.0.0.0 to allow connections from outside container
CMD ["npm", "run", "dev"]
